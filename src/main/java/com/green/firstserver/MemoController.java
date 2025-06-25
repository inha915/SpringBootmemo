package com.green.firstserver;

import com.green.firstserver.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor //생성자 final 멤버필드 초기화(어노테이션)
@RestController //빈(Bean) 등록, 스프링 컨테이너 객체 생성을 대리로 맡긴다. || 요청·응답 담당자
public class MemoController {
//

    private final MemoService memoService;

//    public MemoController(MemoService ms){
//        this.memoService = ms;
//    }


    //DI 받는 법 3가지
    // 1. 필드 주입
    // 2. setter 주입(메소드 주입)
    // 3. 생성자 주입



    @GetMapping("/memo")
    public List<MemoGetRes> getMemo(){
        return memoService.selMemoList();
    }

    @GetMapping("/memo/{id}")
    public MemoGetOneRes getMemo(@PathVariable int id){

        System.out.println("memoId:" + id);
        return memoService.selMemo(id);

    }

    @PostMapping("/memo")
    public String postMemo(@RequestBody MemoPostReq req) { // @RequestBody 는 JSON 데이터를 받을거야
        System.out.println("postMemo:" + req);
        int result = memoService.insMemo(req);
        return result == 1 ? "성공": "실패";
    }

    @PutMapping("/memo")
    public String putMemo(@RequestBody MemoPutReq req) {
        System.out.println("updMemo:" + req);
        int result = memoService.updMemo(req);
        return result == 1 ? "성공": "실패";
    }
    @DeleteMapping("/memo")
    public String deleteMemo(@RequestParam int id) {
        System.out.println("deleteMemo:" + id);
        memoService.delMemo(id);
        return "성공";
    }

}