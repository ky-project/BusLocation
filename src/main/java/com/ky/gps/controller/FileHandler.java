package com.ky.gps.controller;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.util.FileUtil;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * 文件处理控制器
 *
 * @author Daye
 */
@RequestMapping("/file")
@Controller
public class FileHandler {

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(@RequestParam(value = "filename") String filename,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        if(filename.contains("\\")){
            filename.replaceAll("\\\\", "/");
        }
        //模拟文件，myfile.txt为需要下载的文件
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/logs") + filename;
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename, "UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len;
        while ((len = bis.read()) != -1) {
            out.write(len);
            out.flush();
        }
        out.close();
    }

    /**
     * 读取所有日志文件
     *
     * @param request 请求域
     * @return 返回json格式
     */
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper readLogs(HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/logs");
        List<File> fileList = FileUtil.readFiles(realPath);
        List<String> fileNames = FileUtil.getFileNames(fileList, realPath);
        return ResultWrapperUtil.setSuccessOf(fileNames);
    }
}
