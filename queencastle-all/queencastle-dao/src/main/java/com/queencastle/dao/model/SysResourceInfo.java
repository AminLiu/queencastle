package com.queencastle.dao.model;

/**
 * 系统资源，放入七牛服务器<br>
 * http://7xn1k0.com1.z0.glb.clouddn.com<br>
 * 只有新增查询和归档的操作
 * 
 * @author YuDongwei
 *
 */
public class SysResourceInfo extends BaseModel implements Archive {

    private static final long serialVersionUID = -6601064648409051431L;

    /** 上传用户编号 */
    private String userId;
    /** 新生成的文件名，格式为 ：年月日时分秒毫秒加下划线加原始文件名 */
    private String fileName;
    /** 用户上传的文件名 */
    private String originName;
    /** 文件名后缀 */
    private String fileExt;
    /** 对外地址拼接 */
    private String fileKey;

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    private boolean archive;

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }



}
