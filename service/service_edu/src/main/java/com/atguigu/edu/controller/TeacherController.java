package com.atguigu.edu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.entity.VO.TeacherQueryVO;
import com.atguigu.edu.service.TeacherService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lx
 * @since 2023-08-29
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /*
    * 查询所有老师列表
    * */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAllTeacher")
    public R list(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    /*
    * 分页查询
    * */
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(@ApiParam(name = "page",value = "当前页码",required = true)
                      @PathVariable Long page,
                      @ApiParam(name = "limit",value = "每页记录数",required = true)
                      @PathVariable Long limit){
        Page<Teacher> teacherPage = new Page<>(page,limit);
        teacherService.page(teacherPage,null);
        List<Teacher> records = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }


    /*
     * 多条件组合分页查询
     * */
    @ApiOperation(value = "多条件分页讲师列表")
    @PostMapping("pageTeacher/{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQueryVO", value = "查询对象", required = false)
            @RequestBody(required = false) TeacherQueryVO teacherQueryVO){
        Page<Teacher> pageTeacher = new Page<>(page, limit);
        teacherService.pageQuery(pageTeacher, teacherQueryVO);
        List<Teacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    /*
    * 新增讲师
    * */
    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R add(
            @ApiParam(name = "teacher",value = "讲师对象",required = true)
            @RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }

    }

    /*
     * 根据id删除
     * */
    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        boolean removeById = teacherService.removeById(id);
        if(removeById){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /*
    * 根据id修改
    * */
    @ApiOperation(value = "修改")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody Teacher Teacher) {
        boolean flag = teacherService.updateById(Teacher);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /*
    * 根据id查询
    * */
    @ApiOperation(value = "根据id查询")
    @GetMapping("getTeacher/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        try{
            int i = 10/0;
        }catch (Exception e){
            throw new GuliException(20001,"出现自定义异常");
        }
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }
}

