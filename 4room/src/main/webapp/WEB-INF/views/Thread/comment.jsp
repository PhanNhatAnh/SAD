<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<!-- AdminLTE -->
<link href="resources/AdminLTE/css/AdminLTE.css" rel="stylesheet"
	type="text/css" />
<section class="content-mid" style="width: 80%;">
	<div class="content-mid-header">
		<h1>${COMMENT.threadID.name }</h1>
	</div>
	<div class="problem-container">
		
			<div class="problem-item">
				<div class="problem-body">
					<div class='box-body pad'>
						<form action="editComment" method="post">
							<textarea id="editor" name="comment" rows="5" cols="100"
								>${COMMENT.content }                                            
                    </textarea>
							<input type="hidden" name="commentID" value="${COMMENT.commentID }">
							<input type="submit" value="Send">
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