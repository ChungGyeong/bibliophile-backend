package chunggyeong.bibliophile.domain.file.service;

import chunggyeong.bibliophile.domain.file.presentation.dto.response.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileServiceUtils {
    UploadFileResponse uploadImage(MultipartFile file);

    String uploadToS3(MultipartFile file);

    List<UploadFileResponse> uploadImages(List<MultipartFile> files);
}
