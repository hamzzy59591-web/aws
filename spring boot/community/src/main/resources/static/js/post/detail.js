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
		const result = await response.json();
		const post = result.post;
		const files = result.files;
		
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
		
		const 첨부파일박스 = document.querySelector("#files");
		//첨부파일 보여주기
		if(!files || files.length == 0){
			첨부파일박스.innerHTML = `<div class="form-control">없음</div>`;
			return;
		}
		let html = '';
		files.forEach(file=>{
			html +=`
				<a class="form-control" href="/api/upload/${file.savedName}" 
				download=">${file.originalName}" >
				${file.originalName}
				</a>
			`;
		});
		
		첨부파일박스.innerHTML = html;
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


async function createComment1(e){
		e.preventDefault();
		
		const formData = new FormData(e.target);
		const data = Object.fromEntries(formData);
		
		if(data.content.trim().length == 0){
			alert("댓글을 입력하세요.")
			return;
		}
		
		const urlParams = new URLSearchParams(location.search);
		const postId = urlParams.get("num");
	//url:/api/posts/${postId}/comment
	//method : post
	try{
		const response = await authFetch(`/api/posts/${postId}/comment`,{
			method : "post",
			headers : {
				"Content-Type": "application/json"
			},
			body : JSON.stringify(data)
		});
		if (!response.ok) {
			const message = await response.text();
			throw new Error(`댓글 등록 실패: ${response.status}, ${message}`);
		}
		
		const restlt = await response.text();
		//댓글 화면에 배치 
		
	}catch(e){
		console.error(e);
	}
	
	
}