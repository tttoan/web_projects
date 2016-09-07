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
	                    <div id="testmodal" style="padding: 5px 20px;">
	                        <form id="antoform" class="form-horizontal calender" role="form">
	                            <div class="form-group">
	                                <div class="col-sm-12">
	                                	<input type="hidden" id="plan_code" name="plan_code">
                                  		<textarea class="form-control" style="height:100px;" id="descr" name="descr"></textarea>
									</div>
	                            </div>
	                        </form>
	                    </div>
	                </div>
	                <div class="modal-footer">
	                   <button type="button" id="btnUpdateNote" style="float: right" onclick="updateNote()" data-dismiss="modal"
										class="btn btn-primary">Cập nhật</button>
	                </div>
				
			</div>

		</div>
	</div>

</div>

<div id="fc_addNoteDialog" data-toggle="modal"
	data-target="#addNoteDialog" data-backdrop="static"></div>

<style>
       .modal-header .close {
    		margin-top: -2px;
		}
		button.close {
		    padding: 0;
		    cursor: pointer;
		    background: transparent;
		    border: 0;
		    -webkit-appearance: none;
		}
		.close {
		    float: right;
		    font-size: 21px;
		    font-weight: bold;
		    line-height: 1;
		    color: #000;
		    text-shadow: 0 1px 0 #fff;
		    opacity: .2;
		    filter: alpha(opacity=20);
		}
</style>
