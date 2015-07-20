<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<!-- AdminLTE -->
<link href="resources/AdminLTE/css/AdminLTE.css" rel="stylesheet"
	type="text/css" />
<section class="content-mid" style="width: 80%;">
	<div class="content-mid-header">
		<h4>
			<a href="home">4 room</a>
		</h4>
	</div>
	<div class="problem-container">

		<div class="problem-item">
			<div class="problem-body">
				<div class='box-body pad'>
					<form action="createThread" method="post">
						<p>Title:</p>
						<input type="text" name="threadName" class="form-control">
						<p>Body:</p>
						<textarea id="editor" name="contentCom" rows="5" cols="100"
							placeholder="Write comment....">                                            
                    </textarea>

						<input type="submit" value="Create">
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- CK Editor -->
<script src="https://cdn.ckeditor.com/4.4.3/standard/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		// Replace the <textarea id="editor1"> with a CKEditor
		// instance, using default configuration.
		CKEDITOR.replace('editor');
	});
</script>