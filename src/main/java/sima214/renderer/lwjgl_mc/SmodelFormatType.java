package sima214.renderer.lwjgl_mc;

public class SmodelFormatType {
	InterleavedData[] interleaved;
	String match;
	byte numOfElementsPerFace;//Prebake at construction how many floats are there per face
	public SmodelFormatType(InterleavedData[] interleaved,String match) {
		this.interleaved=interleaved;
		this.match=match;
		for(InterleavedData curDat:interleaved){
			numOfElementsPerFace+=curDat.vectorSize;
		}
		numOfElementsPerFace*=3;
	}
}
