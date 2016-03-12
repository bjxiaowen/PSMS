package com.PSMS.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerate {
	private static final AtomicInteger integer = new AtomicInteger(0);
	public static long getId() {
	  long time = System.currentTimeMillis();
	  StringBuilder str = new StringBuilder(18);
	  str.append(time);
	  int intValue = integer.getAndIncrement();
	  if (integer.get() >= 10000) {
	   integer.set(0);
	  }
	  if (intValue < 10) {
	   str.append("000");
	  } else if (intValue < 100) {
	   str.append("00");
	  } else if (intValue < 1000) {
	   str.append("0");
	  }
	  str.append(intValue);
	  return Long.parseLong(str.toString());
	 }
	 public static void main(String[] args) {
	  for (int i = 0; i < 100003; i++) {
	   new Test().start();
	  }
	 }
	 private static class Test extends Thread {
	  @Override
	  public void run() {
	   System.out.println(IDGenerate.getId());
	  }
	 }
	}
