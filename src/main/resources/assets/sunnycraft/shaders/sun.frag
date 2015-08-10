#version 120
varying vec2 texcoord;
uniform sampler2D texsampler;
uniform vec2 timers;//For animation stuff x:deathTimer y:hitTimer
const float offset=0.007f;
vec3 textureColor(vec2 coords){
	vec3 sample=texture2D(texsampler,coords).rgb;//Multisample simulator!!
	//for(int x=-2;x<=2;x++){
	//	for(int y=-2;y<=2;y++)
	//	{
	//		if(y==0 && x==0)
	//		{
	//			continue;
	//		}
	//		sample=mix(sample,texture2D(texsampler,vec2(coords.s+x*offset,coords.t+y*offset)),0.25f);
	//	}
	//}
	return sample;
}
void main() {
	vec3 color=textureColor(texcoord);
	
	if(color.g>0.32f)
	{
		color *= 1-timers.y/4;
	}
	if(color.g<0.30f)
	{
		color *= 0.8+timers.y/4;
	}
    gl_FragColor = vec4(color.rgb,timers.x);
    }
