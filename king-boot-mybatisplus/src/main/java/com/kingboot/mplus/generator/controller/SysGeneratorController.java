package com.kingboot.mplus.generator.controller;

import cn.hutool.core.io.IoUtil;
import com.kingboot.mplus.generator.entity.GenConfig;
import com.kingboot.mplus.generator.service.SysGeneratorService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 代码生成器
 *
 * @author waylen.chi
 * @date 2018-07-30
 */
@RestController
@AllArgsConstructor
@RequestMapping ("/generator")
public class SysGeneratorController {
	private final SysGeneratorService sysGeneratorService;


	/**
	 * 生成代码
	 */
	@SneakyThrows
	@GetMapping ("/code")
	public void generatorCode(@RequestParam String tableName,HttpServletResponse response) {
		GenConfig genConfig = new GenConfig();
		genConfig.setTableName(tableName);
		byte[] data = sysGeneratorService.generatorCode(genConfig);

		response.reset();
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", genConfig.getTableName()));
		response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
		response.setContentType("application/octet-stream; charset=UTF-8");

		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}
}
