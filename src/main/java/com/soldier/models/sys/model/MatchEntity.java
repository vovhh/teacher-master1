package com.soldier.models.sys.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @author soldier
 * @title: SysMatchEntity
 * @projectName teacher
 * @date 19-7-15上午11:52
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "sys_match", schema = "teacher_files", catalog = "")
public class MatchEntity {
    private int matchId;
    private String matchName;
    private Integer matchLevel;//赛事级别
    private Integer matchType;//赛事类型
    private Integer matchAttribute;  //赛事属性：用于添加竞赛项目时，1学生赛事,teacherId用作指导老师；2教师赛事,teacherId用作主持人
    private String organizer;
    private String contractor;
//    private Date matchTime;//赛事年份
    private TeacherEntity teacher;  //赛事负责老师；单向一对一，可以从关联的一方去查询另一方，却不能反向查询
    private Date updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id", nullable = false)
    public int getMatchId() {
        return matchId;
    }
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @Basic
    @Column(name = "match_name", nullable = true, length = 255)
    public String getMatchName() {
        return matchName;
    }
    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    @Basic
    @Column(name = "match_level", nullable = true)
    public Integer getMatchLevel() {
        return matchLevel;
    }
    public void setMatchLevel(Integer matchLevel) {
        this.matchLevel = matchLevel;
    }

    @Basic
    @Column(name = "match_type", nullable = true)
    public Integer getMatchType() {
        return matchType;
    }
    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    @Basic
    @Column(name = "match_attribute", nullable = true)
    public Integer getMatchAttribute() {
        return matchAttribute;
    }
    public void setMatchAttribute(Integer matchAttribute) {
        this.matchAttribute = matchAttribute;
    }

    @Basic
    @Column(name = "organizer", nullable = true, length = 255)
    public String getOrganizer() {
        return organizer;
    }
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    @Basic
    @Column(name = "contractor", nullable = true, length = 255)
    public String getContractor() {
        return contractor;
    }
    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

//    @Basic
//    @Column(name = "match_time", nullable = true)
//    public Date getMatchTime() {
//        return matchTime;
//    }
//
//    public void setMatchTime(Date matchTime) {
//        this.matchTime = matchTime;
//    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchEntity that = (MatchEntity) o;
        return matchId == that.matchId &&
                Objects.equals(matchName, that.matchName) &&
                Objects.equals(matchLevel, that.matchLevel) &&
                Objects.equals(matchType, that.matchType) &&
                Objects.equals(matchAttribute, that.matchAttribute) &&
                Objects.equals(organizer, that.organizer) &&
                Objects.equals(contractor, that.contractor) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, matchName, matchLevel, matchType, matchAttribute, organizer, contractor, updateTime);
    }

    /**
     * 这样子就可以实现单向一对一了，对方实体中什么都不用该，因为只是单向的
     */
    @OneToOne
    @JoinColumn(name = "teacher_id")
    public TeacherEntity getTeacher() {
        return teacher;
    }
    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

}
