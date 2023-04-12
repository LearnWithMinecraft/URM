# URM
In this project I will teach you the Uniform Rectilinear Movement with Minecraft projectiles

# What is Uniform Rectilinear Movement (URM)?

It is a type of motion in which an object moves in a straight line at a constant speed for a certain period of time. The speed is constant, meaning there is no acceleration and no changes in the object's speed or direction during the motion. This constant speed can be described using the equation d = vt, where "d" is the distance traveled, "v" is the constant speed, and "t" is the elapsed time.

# How will we code it today?

Today we will use [Nukkit](https://cloudburstmc.org/articles/) as software to run our program to calculate the URM, we will also use the [Projectiles](https://minecraft-archive.fandom.com/wiki/Category:Projectiles) entities which have an in-game URM, from the position you launch it to where it lands.

First of all, we make a normal Nukkit plugin, we will make a listener class and use two events "ProjectileLaunchEvent" (called when an entity launches a projectile) and "ProjectileHitEvent" (called when the projectile hits something).

We will use nbt to store two pieces of data, the time at which the projectile is launched (initial time) and the vector from where the projectile was launched (initial position / start).

by calling "onProjectileLaunch", we will save the data already mentioned above.

now for the "onProjectileHit" side, we will do all the calculations.

we get the initial time and the initial position, also the current time and the current position.

### Distance: To calculate the distance, we use the "distance" function of the vector3 class of the start vector.

### Time: To calculate the time, we will do a simple operation, subtract the current time from the initial time and divide it by 1000, since both the initial time and the current time are given in milliseconds.

### Velocity: To calculate the speed we divide the distance between the time

demo video: https://youtu.be/ybAc-RN4mQU

# Scheme with the formulas used

![Scheme](https://i.imgur.com/0xJWHY6.png)
