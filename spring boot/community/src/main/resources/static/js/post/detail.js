/*=============================
	DOM 트리 구성이 완료되면 실행
=============================*/

document.addEventListener("DOMContentLoaded",e =>{
	getPost();
});

/*=============================
	게시글을 화면에 출력하는 함수
=============================*/
async function getPost(){
	
		const urlParams = new URLSearchParams(location.search);
		const postId = urlParams.get("num");
		
	try{
		//서버에 게시글을 요청하여 게시글을 가져와서 콘솔에 게시글을 출력하는 코드까지 작성하세요.
		//url: /api/posts/board
		//method: get
		const response = await fetch(`/api/posts/${postId}`);
		
		
		if(!response.ok){
			const result = await response.text();
			alert(result);
			throw Error("게시글이 없거나 삭제됨");
		}
		const post = await response.json();
		
		insertValue("[name=title]", post.title);
		insertValue("[name=writer]", post.memberId);
		insertValue("[name=boardName]", post.board.name);
		insertValue("[name=view]", post.viewCount);
		insertValue("[name=createdAt]", post.createdAt.slice(0,10));
		insertValue("[name=content]", post.content);
		document.querySelector(".up-count").textContent = post.upCount;
		document.querySelector(".down-count").textContent = post.downCount;
		
		//수정/추가버튼 보여주기/감추기
		visibleButtons(false);
		
	}catch(e){
		console.error("게시글 가져오기 실패",e)
	}
}
/*=============================
	입력요소의 value를 수정하는 함수
=============================*/

function insertValue(selector, value){
	document.querySelector(selector).value = value;
	
}

/*=======================================
	게시글 수정/삭제 버튼을 보여줄지를 결정하는 함수
=======================================*/

function visibleButtons(visible){
	if(!visible){
		document.querySelector(".btns").innerHTML = '';
	}
}