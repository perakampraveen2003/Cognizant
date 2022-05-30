package com.booking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Hello<T> {
	T t;
	public Hello(T t){
		this.t=t;
	}
}
