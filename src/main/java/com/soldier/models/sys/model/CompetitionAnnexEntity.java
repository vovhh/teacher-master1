package com.soldier.models.sys.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author soldier
 * @title: CompetitionAnnexEntity
 * @projectName teacher
 * @date 19-7-18下午2:56
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "competition_annex", schema = "teacher_files", catalog = "")
public class CompetitionAnnexEntity {
    private int competitionAnnexId;
    private String filePath;
    private String fileName;
    private String fileType;
    private Integer competitionId;  //伪外键

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competition_annex_id", nullable = false)
    public int getCompetitionAnnexId() {
        return competitionAnnexId;
    }
    public void setCompetitionAnnexId(int competitionAnnexId) {
        this.competitionAnnexId = competitionAnnexId;
    }

    @Basic
    @Column(name = "file_path", nullable = true, length = 255)
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Basic
    @Column(name = "file_name", nullable = true, length = 255)
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_type", nullable = true, length = 255)
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "competition_id", nullable = true)
    public Integer getCompetitionId() {
        return competitionId;
    }
    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitionAnnexEntity that = (CompetitionAnnexEntity) o;
        return competitionAnnexId == that.competitionAnnexId &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(competitionId, that.competitionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competitionAnnexId, filePath, fileName, fileType, competitionId);
    }
}
