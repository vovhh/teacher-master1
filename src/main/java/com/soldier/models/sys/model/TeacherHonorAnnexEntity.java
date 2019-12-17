package com.soldier.models.sys.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName:teacher_files_db
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-6下午1:45
 * @Describe:
 **/
@Entity
@Table(name = "teacher_honor_annex", schema = "teacher_files", catalog = "")
public class TeacherHonorAnnexEntity {
    private long annexId;
    private String filePath;
    private String fileName;
    private String fileType;
    private Long honorId;

    @Id
    @Column(name = "annex_id")
    public long getAnnexId() {
        return annexId;
    }

    public void setAnnexId(long annexId) {
        this.annexId = annexId;
    }

    @Basic
    @Column(name = "file_path")
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Basic
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_type")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "honor_id")
    public Long getHonorId() {
        return honorId;
    }

    public void setHonorId(Long honorId) {
        this.honorId = honorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherHonorAnnexEntity that = (TeacherHonorAnnexEntity) o;
        return annexId == that.annexId &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(honorId, that.honorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annexId, filePath, fileName, fileType, honorId);
    }
}
