package com.adminjava.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.adminjava.entity.SysUser;
import com.google.code.kaptcha.Producer;
import com.adminjava.common.lang.Const;
import com.adminjava.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;

@Slf4j
@RestController
public class AuthController extends BaseController{

	@Autowired
	Producer producer;

	//图片验证码
	@GetMapping("/captcha")
	public Result captcha() throws IOException {

		String key = UUID.randomUUID().toString();
		String code = producer.createText();
		key = "aaaaa";
		code="11111";


		BufferedImage image = producer.createImage(code);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", outputStream);

		BASE64Encoder encoder = new BASE64Encoder();
		String str = "data:image/jpeg;base64,";

		String base64Img = str + encoder.encode(outputStream.toByteArray());

		redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120);
		return Result.success(
				MapUtil.builder()
						.put("token", key)
						.put("captchaImg", base64Img)
						.build()
		);
	}

	//获取用户信息接口
	@GetMapping("/sys/userInfo")
	public Result userInfo(Principal principal) {

		SysUser sysUser = sysUserService.getByUsername(principal.getName());

		return Result.success(MapUtil.builder()
				.put("id", sysUser.getId())
				.put("username", sysUser.getUsername())
				.put("created", sysUser.getCreated())
				.map()
		);
	}


}
