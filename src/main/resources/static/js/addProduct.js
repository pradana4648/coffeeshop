$(function () {
  console.log("Add Product Ready");
});

Dropzone.autoDiscover = false;

// let dropzone = new Dropzone("#product-image", {
//   maxFilesize: 1024,
//   maxFiles: 1,
//   acceptedFiles: "image/*",
//   autoProcessQueue: false,
//   addRemoveLinks: true,
//   url: "/api/v1/product/add",
// });

$("#add-product-form").submit(function (e) {
  e.preventDefault();
  const formData = new FormData();
  const productRequest = {
    name: $(this).find("[name='name']").val(),
    description: $(this).find("[name='description']").val(),
    price: $(this).find("[name='price']").val(),
  };
  const attachmentFile = $(this).find("input[name='image']").prop("files")[0];

  //   dropzone.processQueue();

  formData.append("productRequest", JSON.stringify(productRequest));
  formData.append("image", attachmentFile);
  const credential = btoa("user:12345678");

  console.log(formData.get("productRequest"));
  //   console.log(dropzone.getAcceptedFiles());

  const modal = new bootstrap.Modal("#statusMessageModal", {
    keyboard: false,
  });

  $.ajax({
    url: "/api/v1/product/add",
    method: "POST",
    data: formData,
    processData: false,
    contentType: false,
    success: function (data) {
      if (data.status === 1) {
        $("#statusMessageModal #message").html("Success add Product");
        modal.show();

        setTimeout(() => {
          modal.hide();
        }, 1000);
      } else {
        $("#statusMessageModal #message").html(
          "Failed add Product. Please consult to Administrator"
        );
        console.error(data);
        modal.show();
      }
    },
    crossDomain: true,
    headers: {
      Authorization: `Basic ${credential}`,
    },
  });
});
