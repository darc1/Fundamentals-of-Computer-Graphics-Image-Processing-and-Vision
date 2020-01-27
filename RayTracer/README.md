
Added support for motion blur for sphere and triangle.

The added parameters are x,y,z for movement direction and a size for the movement size.
if these parameters are not specified a static object will be created.
for example:
```
# Sphere:	cx   	cy   	cz  	radius 	mat_idx movement_x movement_y movement_z movement_size
sph		0.5  	0.0    0    0.15    	1
sph		0.25  	0.0    0    0.15    	1   0.5   0.5   0   0.05

```
