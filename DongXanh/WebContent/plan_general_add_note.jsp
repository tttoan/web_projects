<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="container">
	<!-- Modal -->
	<div class="modal fade" id="addNoteDialog" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
					<h4 class="modal-title">Ghi chú</h4>
				</div>
				<div class="modal-body">
					<s:form action="importProductsAction" method="post"
						theme="bootstrap" enctype="multipart/form-data">
						
						 <div class="form-group">
                              <div class="col-sm-9">
                                  <textarea class="form-control" style="height:55px;" id="descr" name="descr"></textarea>
                              </div>
                          </div>

						<hr />

						<input type="submit" value="Import" style="float: right"
							class="btn btn-info btn-lg">
					</s:form>
				</div>
			</div>

		</div>
	</div>

</div>

<div id="fc_addNoteDialog" data-toggle="modal"
	data-target="#addNoteDialog" data-backdrop="static"></div>


