#version 120
varying vec2 texcoord;
uniform sampler2D texsampler;
uniform vec3 timers=vec3(0f);//For animation stuff x:deathTimer y:hitTimer z:intensity(NYI)
void main() {
	vec3 color=texture2D(texsampler, texcoord).rgb;
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
