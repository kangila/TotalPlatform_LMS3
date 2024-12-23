package com.example.demo.lms.file;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class FileService {
	@Autowired
	private FileRepository fr;
	
	public Integer save(HttpServletRequest request, MultipartFile files) throws Exception {
		
		String sourceFileName = files.getOriginalFilename(); //원본 파일명
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); //파일 확장자명(소문자)
		File destinationFile; //파일 객체
		String destinationFileName; //실제 서버에 저장되는 파일명 > 이유: 각각 다른 사용자의 동일한 파일명을 한 폴더에 저장시키기 위해서
		String fileUrl = "C:\\Users\\504호\\git\\TotalPlatform_LMS3\\pinguClass\\src\\main\\resources\\static\\files\\"; //파일이 저장되는 서버 공간
		
		//해당 경로에 파일명이 있다면 랜덤 파일명 재생성 후 파일 객체 생성
		do {
			destinationFileName = RandomStringUtils.randomAlphabetic(32) + "." + sourceFileNameExtension;
			destinationFile = new File(fileUrl + destinationFileName);
		} while (destinationFile.exists()); //File.exists() : 파일 발견시 ture 반환, 미발견시 false 반환
		
		destinationFile.getParentFile().mkdirs(); //부모 폴더가 없다면 생성
		files.transferTo(destinationFile); //파일 복사
		
		com.example.demo.lms.entity.File f = new com.example.demo.lms.entity.File();
		f.setFileName(destinationFileName); //실제 서버에 저장되는 파일명
		f.setOriginalFileName(sourceFileName); //사용자가 올린 파일명
		f.setFileUrl(fileUrl);
		f.setFileType(sourceFileNameExtension);
		f.setLastUpdate(LocalDateTime.now());
		f.setDeleteYn("n");
		this.fr.save(f); //DB file 테이블에 저장
		
		return this.fr.findFileIdByFileName(destinationFileName);
	}
}
