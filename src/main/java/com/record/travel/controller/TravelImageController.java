package com.record.travel.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.record.travel.dto.TravelImageDTO;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class TravelImageController {
	
	@Value("${com.record.travel.path.upload}")
	private String uploadPath;
	
	@PostMapping("/uploadImage")
	public ResponseEntity<List<TravelImageDTO>> uploadImage(MultipartFile[] uploadImages) {
		
		List<TravelImageDTO> resultImageList = new ArrayList<>();
		
		for(MultipartFile uploadImage : uploadImages) {
			
			//이미지 파일만 업로드 가능
			if(uploadImage.getContentType().startsWith("image") == false) {
				log.warn("---------이미지 파잃이 아닙니다!!-----------");
				return;
			}
			
			//실제 이미지 파일 이름은 전체 경로 들어오기 땨문
			String originalName = uploadImage.getOriginalFilename();
			String imageFileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
			
			log.info("이미지 파일 이름 : " + imageFileName);
			
			//동일 폴더에 너무 많은 파일 넣으면 성능 저하 -> 년/월/일 폴더 생성해 분산시킴
			String folderPath = makeFolder();
			
			//동일 이름 파일 업로드 방지 -> UUID 이용해 고유값 만들어 사용
			String uuid = UUID.randomUUID().toString();
			
			//저장할 파일 이름 중간에 "_"를 이용해 구분
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + imageFileName;
			
			Path savePath = Paths.get(saveName);
			
			try {
				uploadImage.transferTo(savePath);
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		String folderPath = str.replace("//", File.separator);
		
		//폴더 만들기
		File uploadPathFolder = new File(uploadPath, folderPath);
		
		if(uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		}
		return folderPath;
	}
	
}
