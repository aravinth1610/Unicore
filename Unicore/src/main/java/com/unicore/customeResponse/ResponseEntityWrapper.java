package com.unicore.customeResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author aravinth
 * @since 2024
 *
 *        A sample source file for the code formatter preview
 */

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "message", "data", "errors" })
public class ResponseEntityWrapper<T> extends DataResponse<T> {

	@JsonProperty("status")
	private String status;

	@JsonProperty("errors")
	private ErrorResponse<?> error;

	@JsonProperty("message")
	private String message;


	public ResponseEntityWrapper(T data) {
		super();
		this.status = "Success";
		this.setData(data);
		this.message = "Operation completed successfully.";
	}

	public ResponseEntityWrapper() {
		super();
		this.status = "Success";
		this.message = "Operation completed successfully.";
	}

	public ResponseEntityWrapper(String message, ErrorResponse<?> error) {
		super();
		this.status = "Failure";
		this.message = message;
		this.error = error;
	}
	
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntityWrapper<T> Ok(T data) {
		this.status = "Success";
		this.setData(data);
		this.message = "Operation completed successfully.";
		return this;
	}

	@ResponseStatus(HttpStatus.OK)
	public ResponseEntityWrapper<T> Ok() {
		this.status = "Success";
		this.message = "Operation completed successfully.";
		return this;
	}

}
