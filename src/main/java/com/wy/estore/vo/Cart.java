package com.wy.estore.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItem> map = new LinkedHashMap<String, CartItem>();
	private double total;

	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	public double getTotal() {
		return total;
	}

	public void addCart(CartItem cartItem){
		String bid = cartItem.getBook().getBid();
		if(map.containsKey(bid)){
			CartItem _cartItem = map.get(bid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			map.put(bid, cartItem);
		}
		total += cartItem.getSubtotal();
	}

	public void removeCart(String bid){
		CartItem cartItem = map.remove(bid);
		total -= cartItem.getSubtotal();
	}

	public void clearCart(){
		map.clear();
		total = 0;
	}
}
