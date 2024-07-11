package kr.or.nextit.bookmarket.common;

import lombok.*;

@RequiredArgsConstructor
@Data
public class SearchVO {
    private final String searchType;
    private final String searchWord;
    private int firstRecordIndex;
    private int lastRecordIndex;
}
