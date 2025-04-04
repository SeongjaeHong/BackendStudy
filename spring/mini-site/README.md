# 미니 게시판 사이트 만들기 (3/16 ~ Present)

---
## Database schema
![DB schema](https://github.com/SeongjaeHong/BackendStudy/blob/master/spring/mini-site/images/DB_schema.png?raw=true)

---
## 학습 사항
### General Concept
* DTO(Data Transfer Object) - 2025. 03. 22
    * 계층간 데이터 전송 객체
    * API와 비즈니스 로직 분리
    * 필요 정보만 전송함으로써 네트워크 전송량 저감 및 내부 로직 보안 향상


* Mapper (Entity <-> DTO) - 2025. 04. 04
  * Entity와 DTO 사이의 변환을 손쉽게 해주는 기능
  * 변환 과정이 Boilerplate 코드와 다를 바 없고, 변환 코드 작성에서 실수가 빈번히 발생할 수 있음
  * 코드 개발 및 유지보수 효율성 향상을 위하여 Mapper를 작성하는 것이 좋을 것 같음

    (MapStruct 라이브러리는 Mapper 코드를 생성하는 빠르고 안정적인 라이브러리로 알려져있음)
  * 본 프로젝트에서 구현한 DTO들이 단순하고, 그 수가 적으므로 Mapper의 필요성을 느끼지 못하여 적용하지 않음

    (DTO 내부에서 toEntity 같은 함수로 대체)

### JPA
* orphanRemoval - 2025. 03. 28
  * 부모 Entity 제거 시 자식 Entity 모두 제거
    * 부모 Entity가 제거 되더라도 자식 Entity 보존을 위해 사용하지 않기로 결정
    
      (탈퇴한 사용자가 작성한 글/댓글, 상위 댓글이 삭제된 대댓글 등)

### JAVA
* Record class - 2025. 03. 22
    * Boiler plate 코드(생성자, Getter, equals, hashcode, toString) 생략
    * 데이터 불변성 보장
    * 데이터 보관용 객체 명확화
    * DTO 객체 생성에 사용


* LinkedHashMap - 2025. 03. 28
  * 사용자가 작성한 댓글, 게시글 정보 저장  
    * 사용자가 작성한 글 조회 시 작성 순서대로 조회
    * 사용자가 작성한 글이 많아져도 삭제 시간을 O(1)으로 유지

### Git
* revert - 2025. 04. 03
  * 원격 저장소에 저장된 commit을 취소하기 위한 새로운 commit 생성