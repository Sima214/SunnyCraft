#version 120
varying vec2 texcoord;
uniform int time=0;
void main() {
  gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
  texcoord = vec2(gl_MultiTexCoord0)+(mod(time,600)/600);
}
