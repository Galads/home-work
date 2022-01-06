package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.NonNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FileAccountRepository implements AccountRepository {
    private String path;

    public FileAccountRepository(@NonNull String path) {
        this.path = path;
    }

    @Override
    public Set<Long> getAllAccountsByClientId(long clientId) throws IOException {
        Set<Long> accountsByClientId = new HashSet<>();

        try (InputStream fis = new FileInputStream(path);
             Reader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {

            for (String str = br.readLine(); str != null; str = br.readLine()) {
                if (str.contains("\"clientId\"")) {
                    long valueId = getSecondValue(str, 1);
                    if (valueId == clientId) {
                        String secStr = br.readLine();
                        if (secStr != null && secStr.contains("\"number\"")) {
                            long number = getSecondValue(secStr, 0);
                            accountsByClientId.add(number);
                        }
                    }
                }
            }
        }
        return accountsByClientId;
    }

    private long getSecondValue(String str, int rightIndexPosition) {
        String[] splitStr = str.split(":");
        String valueAccount = splitStr[splitStr.length - 1].trim();
        return Long.parseLong(valueAccount.substring(0, valueAccount.length() - rightIndexPosition));
    }

    @Override
    public boolean updateClientNumber(long clientId,
                                      long oldNumber,
                                      long newNumber) throws IOException {
        List<String> strBuffer = new LinkedList<>();
        boolean result;

        try (InputStream fis = new FileInputStream(path);
             Reader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {

            for (String str = br.readLine(); str != null; str = br.readLine())
                strBuffer.add(str + System.lineSeparator());
        }

        result = changeNumberInStr(strBuffer, clientId, oldNumber, newNumber);

        try (OutputStream os = new FileOutputStream(path);
             Writer osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(osw)) {

            for (String s : strBuffer)
                bw.write(s);
        }
        return result;
    }

    private boolean changeNumberInStr(List<String> repStrBuf,
                                      long clientId,
                                      long oldNumber,
                                      long newNumber) {
        boolean res = false;

        for (int i = 0; i < repStrBuf.size() - 1; i++) {
            if (repStrBuf.get(i).contains("\"clientId\"")) {
                long valueId = getSecondValue(repStrBuf.get(i), 1);
                if (valueId == clientId) {
                    String secStr = repStrBuf.get(i + 1);
                    if (secStr != null && secStr.contains("\"number\"")
                            && getSecondValue(secStr, 0) == oldNumber) {
                        int index = repStrBuf.indexOf(secStr);
                        repStrBuf.set(index, secStr.replace(Long.toString(oldNumber), Long.toString(newNumber)));
                        res = true;
                    }
                }
            }
        }
        return res;
    }

    @Override
    public Set<String> getAllPaymentDetails(long clientId) {
        return null;
    }

}
