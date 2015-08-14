package sima214.renderer.lwjgl_mc;

import sima214.core.DataTypes;

public class SmodelFormatType {
	InterleavedData[] interleaved;
	String match;
	byte numOfElementsPerFace;//Prebake at construction how many floats are there per face
	byte numOfBytesPerFace;//TODO Done in rush
	public SmodelFormatType(InterleavedData[] interleaved,String match) {
		this.interleaved=interleaved;
		this.match=match;
		for(InterleavedData curDat:interleaved){
			numOfElementsPerFace+=curDat.vectorSize;
		}
		numOfElementsPerFace*=3;
		numOfBytesPerFace=(byte) (numOfElementsPerFace*DataTypes.FLOAT.getBytes());
	}
}
