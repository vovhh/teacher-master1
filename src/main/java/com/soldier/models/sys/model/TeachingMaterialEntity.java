package com.soldier.models.sys.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName:teacher_files_db
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-5上午8:44
 * @Describe:教材
 **/
@Entity
@Table(name = "teaching_material", schema = "teacher_files", catalog = "")
public class TeachingMaterialEntity {
    private Long materialId;
    private String materialName;
//    private Integer editor;
    private String press;
    private String publishTime;
    private String remarks;
    private Long deptId;//保存添加用户的部门id
    private TeacherEntity teacher;  //主编：单向一对一，可以从关联的一方去查询另一方，却不能反向查询

    @Id
    @Column(name = "material_id")
    public Long getMaterialId() {
        return materialId;
    }
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    @Basic
    @Column(name = "material_name")
    public String getMaterialName() {
        return materialName;
    }
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Basic
    @Column(name = "press")
    public String getPress() {
        return press;
    }
    public void setPress(String press) {
        this.press = press;
    }

    @Basic
    @Column(name = "publish_time")
    public String getPublishTime() {
        return publishTime;
    }
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "dept_id", nullable = true)
    public Long getDeptId() {
        return deptId;
    }
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachingMaterialEntity that = (TeachingMaterialEntity) o;
        return materialId == that.materialId &&
                Objects.equals(materialName, that.materialName) &&
                Objects.equals(press, that.press) &&
                Objects.equals(publishTime, that.publishTime) &&
                Objects.equals(remarks, that.remarks) &&
                Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, materialName, press, publishTime, remarks, deptId);
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
