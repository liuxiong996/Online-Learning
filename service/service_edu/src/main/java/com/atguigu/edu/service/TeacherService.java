package com.atguigu.edu.service;

import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.entity.VO.TeacherQueryVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lx
 * @since 2023-08-29
 */
public interface TeacherService extends IService<Teacher> {
    void pageQuery(Page<Teacher> pageTeacher, TeacherQueryVO teacherQueryVO);
}
