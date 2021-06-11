# 프로젝트 개요

토이프로젝트 - 게시판 만들기


## 개발 환경
|기술(환경)|버전|
|------|---|
|STS|4.7.1.RELEASE|
|Spring Boot|2.4.2|
|Thymeleaf|3|
|Mybatis|2.1.4|
|JPA||
|Java|1.8|
|MariaDB|10.4.17|
|OS|Window|
|Maven| |
|Git||
|Chrome|89.0.4389.114(x64)|



## 개발 표준

### SQL
- 모든 표현문은 오른쪽을 기준으로 정렬한다.
- FROM절 조인은 ANSI를 따른다.
```sql
 SELECT *
   FROM table
  WHERE 1=1;

 SELECT a
       ,b
       ,c
   FROM table;

 SELECT a.emp_id
       ,b.emp_id
   FROM user a join user b
     ON a.emp_id = b.emp_id;
```



### API
- 메서드의 표현은 어노테이션의 값으로 표현한다.(GetMapping X)
- @PathVariable을 사용해 API의 값을 표현한다.
- value 값은 배열 형태로 정의한다. 
- 기능, 작성자, 반환 값, 시작 날짜를 API위에 주석으로 표시한다.
```java
/**
  * 게시물 삭제
  * choi.hak.jun
  * return 1 : 성공 , 0 : 실패
  * Start 2021.03.25
  */
@RequestMapping(value = {"/board/{boardId}"}, method = RequestMethod.DELETE)
	public int deleteBoard(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer boardId) {

		int result = boardService.deleteBoard(boardId);
		
		return result;
	}
```


### JAVA
- 모든 Controller는 @RestContoller 어노테이션을 사용한다.
- Service는 impl를 구현하지 않는다.(필요 시 구현)
- @Transactional 어노테이션을 Class에 붙인다.
#### 역할별 suffix

- Controller : *Controller (RestController 포함)
- Service : *Service
- Mapper : *Mapper
- Repository : *Repository 
- Entity : *Entity
- DTO : *Dto
- Domain : 접미사를 붙이지 않는다. (일반적으로 - - setter, getter만 존재하는 클래스의 경우 Domain이라고 하지 않는다. 이경우 Dto를 사용할 것.)
- Util : *Util

## 마지막 README 업데이트
2021.04.26
