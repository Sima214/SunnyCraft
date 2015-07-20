#version 120
varying vec2 texcoord;
uniform sampler2D texsampler;
uniform vec3 timers;//For animation stuff x:intensity y:deathTimer z:hitTimer
const float offset=0.007f;
vec4 textureColor(vec2 coords){
	vec4 sample=texture2D(texsampler,coords);//Multisample simulator!!
	for(int x=-2;x<=2;x++){
		for(int y=-2;y<=2;y++)
		{
			if(y==0 && x==0)
			{
				continue;
			}
			sample=mix(sample,texture2D(texsampler,vec2(coords.s+x*offset,coords.t+y*offset)),0.25f);
		}
	}
	return sample;
}
void main() {
	vec4 color=textureColor(texcoord);
	
	if(color.g>0.32f)
	{
		color *=1-timers.z/4;
	}
	if(color.g<0.30f)
	{
		color*=0.8+timers.z/4;
		color=clamp(color,0f,1f);
	}
    gl_FragColor = vec4(color.rgb,timers.y);
    }
