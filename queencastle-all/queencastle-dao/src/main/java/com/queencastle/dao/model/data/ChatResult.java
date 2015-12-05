package com.queencastle.dao.model.data;

import java.util.List;

public class ChatResult {
    private int errcode;
    private String errmsg;
    private int retcode;
    private List<ChatRecord> recordlist;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public List<ChatRecord> getRecordlist() {
        return recordlist;
    }

    public void setRecordlist(List<ChatRecord> recordlist) {
        this.recordlist = recordlist;
    }

}
