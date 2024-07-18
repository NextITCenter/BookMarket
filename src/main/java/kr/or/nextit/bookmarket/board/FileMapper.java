package kr.or.nextit.bookmarket.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    // 첨부파일 저장
    void saveFiles(List<FileVO> files);
    // 한 게시글의 파일 목록 가져오기
    List<FileVO> selectFiles(int boardNo);
    // 한 개의 파일을 다운로드 할 때 파일 정보 가져오기
    FileVO selectFile(int id);
    void deleteFile(long boardNo);
}
