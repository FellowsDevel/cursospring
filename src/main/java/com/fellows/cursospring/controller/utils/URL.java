package com.fellows.cursospring.controller.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Integer> decodeIntList( String s ) {
		return Arrays.asList( s.split( "," ) ).stream().map( it -> Integer.parseInt( it ) )
				.collect( Collectors.toList() );

	}

	public static String decodeParam( String a ) {
		try {
			return URLDecoder.decode( a, "UTF-8" );
		} catch ( UnsupportedEncodingException e ) {
			return "";
		}
	}
}
