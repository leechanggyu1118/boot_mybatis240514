
console.log("userModify.js in!!");

document.getElementById('deleteBtn').addEventListener('click', (e)=>{
    const isDelete = confirm("정말로 회원삭제를 하겠습니까?");
    // confirm => 예 아니오 콘솔창이 뜨고 true / false 값으로 나온다.
    console.log(isDelete);
    if(isDelete){
        alert("회원삭제가 완료되었습니다.");
       return location.href = "/member/deleteMember";
    }
});

document.getElementById("updateBtn").addEventListener('click',(e)=>{
    alert("수정이 완료되었습니다.");
    alert("다시 로그인 해주세요.");

});

