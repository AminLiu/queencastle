package com.queencastle.service.test.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.queencastle.dao.model.data.SaleData;
import com.queencastle.service.test.BaseTest;

public class SaleDataTester extends BaseTest {

    @Test
    public void pushIntoDB() throws Exception {
        for (int i = 1; i < 6; i++) {
            String fileName = "E:/" + i + i + "_new.txt";
            System.out.println(fileName);
            List<String> lines = FileUtils.readLines(new File(fileName));
            handlerLines(lines);

        }
    }

    private void handlerLines(List<String> lines) {
        List<SaleData> dataList = new ArrayList<SaleData>();
        for (String line : lines) {
            String[] array = line.split(",");
            SaleData data = new SaleData();
            data.setOrderNo(array[0]);
            data.setUserName(array[1]);
            data.setPhone(array[2]);
            String adds = array[3];
            if (StringUtils.isNoneBlank(adds)) {
                String[] addArray = adds.split(" ");
                if (addArray.length >= 4) {
                    data.setProvince(addArray[0]);
                    data.setCity(addArray[1]);
                    data.setArea(addArray[2]);
                    List<String> aList = new ArrayList<String>();
                    for (int k = 3; k < addArray.length; k++) {
                        aList.add(addArray[k]);
                    }
                    data.setAddress(StringUtils.join(aList, ""));
                } else {
                    data.setAddress(adds);
                }
            }
            //
            dataList.add(data);
            if (dataList.size() == 500) {
                saleDataService.batchInsert(dataList);
                dataList.clear();
            }
        }
        if (dataList.size() > 0) {
            saleDataService.batchInsert(dataList);
            dataList.clear();
        }
    }
}
