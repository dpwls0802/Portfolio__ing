package com.record.travel.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.record.travel.dto.TravelImageDTO;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class TravelImageController {
	
	@Value("${com.record.travel.path.upload}")
	private String uploadPath;
	
	//이미지 업로드
	@PostMapping("/uploadImage")
	public ResponseEntity<List<TravelImageDTO>> uploadImage(MultipartFile[] uploadImages) {
		
		List<TravelImageDTO> resultImageList = new ArrayList<>();
		
		for(MultipartFile uploadImage : uploadImages) {
			
			//이미지 파일만 업로드 가능
			if(uploadImage.getContentType().startsWith("image") == false) {
				log.warn("---------이미지 파잃이 아닙니다!!-----------");
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			
			//실제 이미지 파일 이름은 전체 경로 들어오기 땨문
			String originalName = uploadImage.getOriginalFilename();
			String imageName = originalName.substring(originalName.lastIndexOf("\\") + 1);
			
			log.info("이미지 파일 이름 : " + imageName);
			
			//동일 폴더에 너무 많은 파일 넣으면 성능 저하 -> 년/월/일 폴더 생성해 분산시킴
			String folderPath = makeFolder();
			
			//동일 이름 파일 업로드 방지 -> UUID 이용해 고유값 만들어 사용
			String uuid = UUID.randomUUID().toString();
			
			//저장할 파일 이름 중간에 "_"를 이용해 구분
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + imageName;
			
			Path savePath = Paths.get(saveName);
			
			try {
				//원본 파일 저장
				uploadImage.transferTo(savePath);
				
				//섬네일 생성
				String thumbnailSavaName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + imageName;
				
				//섬네일 파일 이름은 중간에 s_로 시작하도록
				File thumbnailFile = new File(thumbnailSavaName);
				
				//섬네일 생성
				Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);
				
				resultImageList.add(new TravelImageDTO(imageName, uuid, folderPath));
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		return new ResponseEntity<>(resultImageList, HttpStatus.OK);
	}
	
	//폴더 만들기
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
	
	//이미지 보여주기
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String imageName) {
		ResponseEntity<byte[]> result = null;
		
		try {
			String srcImageFileName = URLDecoder.decode(imageName, "UTF-8");
			log.info("이미지 파일 이름 : "+srcImageFileName);
			
			File file = new File(uploadPath + File.separator + srcImageFileName);
			log.info("파일 : " + file);
			
			HttpHeaders header = new HttpHeaders();
			
			//MIME 타입 처리
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			//파일 데이터 처리
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
		
	}

	//이미지 삭제하기
	@PostMapping("/removeImage")
	public ResponseEntity<Boolean> removeImage(String imageName) {
		String srcImageFileName = null;
		
		try {
			srcImageFileName = URLDecoder.decode(imageName, "UTF-8");
			File file = new File(uploadPath + File.separator + srcImageFileName);
			boolean result = file.delete();
			
			File thumbnail = new File(file.getParent(), "s_" + file.getName());
			result = thumbnail.delete();
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	
	
}
