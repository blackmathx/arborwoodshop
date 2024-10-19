



const createListingForm = document.getElementById("createListingForm");

createListingForm.addEventListener("submit", async event => {
    event.preventDefault();

    var imageOnePath = "";
    var imageTwoPath = "";
    var imageThreePath = "";

    const allowImageUploads = false;

    if(!listingValidation()){

        const fileOne = document.getElementById("imageInputOne").files[0];

        if(fileOne != null){

            var fileOneType = fileOne.type;
            var fileOneSize = fileOne.size;

            if(allowImageUploads){
                const url = await fetch("/user/api/get-upload-url?type=" + fileOneType + "&size=" + fileOneSize).then(res => res.text());

                if(url != "null"){
                    const result = await fetch(url, {
                        method: 'PUT',
                        headers: { 'Content-Type': fileOneType },
                        body: fileOne
                    });

                    if(result.status === 200){
                        //console.log("upload successful");
                    }  else {
                        const errorText = await result.text(); // Extract the response body as text
                        console.log("Error:", result.status, result.statusText, errorText); // Log status, statusText, and body
                    }

                    // parse image url
                    let imageUrl = url.split('?')[0];
                    imageOnePath = imageUrl;
                }
            } else {
                imageOnePath = "http://localhost:8080/image/default3.jpg";
            }
        }

        const fileTwo = document.getElementById("imageInputTwo").files[0];
        if(fileTwo != null){

            if(allowImageUploads){
                var fileTwoType = fileTwo.type;
                var fileTwoSize = fileTwo.size;

                const url = await fetch("/user/api/get-upload-url?type=" + fileTwoType + "&size=" + fileTwoSize).then(res => res.text());

                if(url != "null"){
                    const result = await fetch(url, {
                        method: 'PUT',
                        headers: { 'Content-Type': fileTwoType },
                        body: fileTwo
                    });

                    if(result.status === 200){
                        //console.log("upload successful")
                    }  else {
                        const errorText = await result.text(); // Extract the response body as text
                        console.log("Error:", result.status, result.statusText, errorText); // Log status, statusText, and body
                    }

                    let imageUrl = url.split('?')[0];
                    imageTwoPath = imageUrl;
                }
            } else {
                imageTwoPath = "http://localhost:8080/image/default4.jpg";
            }
        }

        const fileThree = document.getElementById("imageInputThree").files[0]
        if(fileThree != null){

            var fileThreeType = fileThree.type;
            var fileThreeSize = fileThree.size;

            if(allowImageUploads){
            const url = await fetch("/user/api/get-upload-url?type=" + fileThreeType + "&size=" + fileThreeSize).then(res => res.text());

            if(url != "null"){
                const result = await fetch(url, {
                    method: 'PUT',
                    headers: { 'Content-Type': fileThreeType },
                    body: fileThree
                });

                if(result.status === 200){
                    //console.log("upload successful")
                }  else {
                    const errorText = await result.text(); // Extract the response body as text
                    console.log("Error:", result.status, result.statusText, errorText); // Log status, statusText, and body
                }

                let imageUrl = url.split('?')[0];
                imageThreePath = imageUrl;
            }
            } else {
                imageThreePath = "http://localhost:8080/image/default5.jpg";
            }
        }

        document.getElementById("imageOne").value = imageOnePath;
        document.getElementById("imageTwo").value = imageTwoPath;
        document.getElementById("imageThree").value = imageThreePath;
        submitListingForm();

    } else {
        alert("validation failed");
    }
})


function submitListingForm(){
    createListingForm.submit();
}

function listingValidation(allowImageUploads){
    var invalid = false;
    var title = document.getElementById("title").value.replace(/\s/g,"");
    var price = document.getElementById("price").value.replace(/\s/g,"");
    var desc = document.getElementById("description").value.replace(/\s/g,"");
    var regex = /^\d+(?:\.\d{0,2})?$/;

    if(title == "") invalid = true;
    if(price == "" || !regex.test(price))  invalid = true;
    if(desc == "") invalid = true;

    if(allowImageUploads) {
        const fileOne = document.getElementById("imageInputOne").files[0];
        const fileTwo = document.getElementById("imageInputTwo").files[0];
        const fileThree = document.getElementById("imageInputThree").files[0];

        if(!fileOne && !fileTwo && !fileThree) invalid = true;
        if(fileOne.size * 0.000001 > 1.0){
            invalid = true;
            alert("File Size Invalid: " + fileOne.size * 0.000001 + "MB");
        }
    }

    return invalid;
}


function removeImageOne(){
    document.getElementById('imagePreviewOne').src = "";
    document.getElementById('imageInputOne').value = "";
}
function removeImageTwo(){
    document.getElementById('imagePreviewTwo').src = "";
    document.getElementById('imageInputTwo').value = "";
}
function removeImageThree(){
    document.getElementById('imagePreviewThree').src = "";
    document.getElementById('imageInputThree').value = "";
}

function img1(){
    document.getElementById('imagePreviewOne').src = window.URL.createObjectURL(document.getElementById('imageInputOne').files[0]);
}
function img2(){
    document.getElementById('imagePreviewTwo').src = window.URL.createObjectURL(document.getElementById('imageInputTwo').files[0]);
}
function img3(){
    document.getElementById('imagePreviewThree').src = window.URL.createObjectURL(document.getElementById('imageInputThree').files[0]);
}
