package com.soldier.common.vo;

/**
 * @ProjectName:teacher
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-10下午6:56
 * @Describe:
 **/
public class SysDataModelBean {

    //  部门
    private Long deptId;
    //  用户数量
    private Long userAllCount;
    //  教学竞赛数量
    private Long competitionCount;
    //  教学竞赛数量-学生
    private Long competitionStuCount;
    //  教学竞赛数量-教师
    private Long competitionTeaCount;
    //  教师项目数量
    private Long teacherItemCount;
    //  学术论文数量
    private Long paperCount;
    //  教材数量
    private Long teachingMaterialCount;
    //  教学成果数量
    private Long teachingResultCount;
    //  教学荣誉数量
    private Long teacherHonorCount;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getUserAllCount() {
        return userAllCount;
    }

    public void setUserAllCount(Long userAllCount) {
        this.userAllCount = userAllCount;
    }

    public Long getCompetitionCount() {
        return competitionCount;
    }

    public void setCompetitionCount(Long competitionCount) {
        this.competitionCount = competitionCount;
    }

    public Long getCompetitionStuCount() {
        return competitionStuCount;
    }

    public void setCompetitionStuCount(Long competitionStuCount) {
        this.competitionStuCount = competitionStuCount;
    }

    public Long getCompetitionTeaCount() {
        return competitionTeaCount;
    }

    public void setCompetitionTeaCount(Long competitionTeaCount) {
        this.competitionTeaCount = competitionTeaCount;
    }

    public Long getTeacherItemCount() {
        return teacherItemCount;
    }

    public void setTeacherItemCount(Long teacherItemCount) {
        this.teacherItemCount = teacherItemCount;
    }

    public Long getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(Long paperCount) {
        this.paperCount = paperCount;
    }

    public Long getTeachingMaterialCount() {
        return teachingMaterialCount;
    }

    public void setTeachingMaterialCount(Long teachingMaterialCount) {
        this.teachingMaterialCount = teachingMaterialCount;
    }

    public Long getTeachingResultCount() {
        return teachingResultCount;
    }

    public void setTeachingResultCount(Long teachingResultCount) {
        this.teachingResultCount = teachingResultCount;
    }

    public Long getTeacherHonorCount() {
        return teacherHonorCount;
    }

    public void setTeacherHonorCount(Long teacherHonorCount) {
        this.teacherHonorCount = teacherHonorCount;
    }
}
