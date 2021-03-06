package com.soldier.models.sys.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName:teacher_files_db
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-5上午8:44
 * @Describe:
 **/
@Entity
@Table(name = "teaching_material_annex", schema = "teacher_files", catalog = "")
public class TeachingMaterialAnnexEntity {
    private Long annexId;
    private String filePath;
    private String fileName;
    private String fileType;
    private Long materialId;

    @Id
    @Column(name = "annex_id")
    public Long getAnnexId() {
        return annexId;
    }
    public void setAnnexId(Long annexId) {
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
    @Column(name = "material_id")
    public Long getMaterialId() {
        return materialId;
    }
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachingMaterialAnnexEntity that = (TeachingMaterialAnnexEntity) o;
        return annexId == that.annexId &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(materialId, that.materialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annexId, filePath, fileName, fileType, materialId);
    }
}
