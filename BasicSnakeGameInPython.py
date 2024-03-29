import pygame, sys, time, random

speed = 30

#sizes for windows

frame_size_x = 800
frame_size_y = 600

check_errors = pygame.init()

if(check_errors[1] > 0):
    print("Error" + check_errors[3])
else:
    print("Game Sucessfully Initialized")

#Initialization of Game window

pygame.display.set_caption("Snake Game")
game_window = pygame.display.set_mode(frame_size_x, frame_size_y)

#colors
black = pygame.Color(0,0,0)
white = pygame.Color(255,255,255)
red = pygame.Color(255,0,0)
green = pygame.Color(0,255,0)
blue = pygame.Color(0,0,255)

fps_controller = pygame.time.Clock()

#individual snake game size
square_size = 10

#Declaring the parameters of the body
def init_vars():
    global head_pos, snake_body, food_pos, food_spawn, direction
    direction = "Right"
    head_pos = [120, 60]
    snake_body = [[120, 60]]
    food_pos = [random.randrange(1,(frame_size_x // square_size)) *  square_size,
                random.range(1,(frame_size_y // square_size)) * square_size]
    food_spawn = True
    score = 0

init_vars()

def show_score(choice, color , font , size):
     score_font = pygame.font.SysFont(font, size)
     score_surface = score_font.render("Score : " + str(score), True, color)
     score_rect = score_surface.get_rect()
     if choice == 1:
          score_rect.midtop = (frame_size_x / 10, 15)
     else:
        score_rect.midtop = (frame_size_x / 2, frame_size_y / 1.50)

     game_window.blit(score_surface, score_rect)


# looping the game 


#Assigning keys to for movement of the snake
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
        elif event.type == pygame.KEYDOWN:
            if (event.key == pygame.K_UP or event.key == ord("w") or event.key == ("W") # for the moving the snake forward
                and direction != "DOWN"):
                direction = "UP"
            elif (event.key == pygame.K_DOWN or event.key == ord("s") or event.key == ("S") # moving the snake downward
                  and direction != "UP"):
                  direction = "DOWN"
            elif (event.key == pygame.K_LEFT or event.key == ord("a") or event.key == ("D") #Moving the snake left
                  and direction != "RIGHT"):
                  direction = "LEFT"
            elif (event.key == pygame.K_RIGHT or event.key == ord("d") or event.key == ("R") #Moving the snake right
                  and direction != "LEFT"):
                  direction = "RIGHT"

    #Assigining the logic of keys to square blocks
    if direction == "UP":
         head_pos[1] -= square_size
    elif direction == "DOWN":
         head_pos[1] += square_size
    elif direction == "LEFT":
         head_pos[0] -= square_size
    else:
         head_pos[0] += square_size
        
    if head_pos[0] < 0:
         head_pos[0] = frame_size_x - square_size
    elif head_pos[0] > frame_size_x - square_size:
         head_pos[0]
    elif head_pos[1] < 0:
         head_pos[1] = frame_size_y - square_size
    elif head_pos[1] > frame_size_y - square_size:
         head_pos[1] = 0
        
    
    #Eating Fruit

    snake_body.insert(0, list(head_pos))
    if head_pos[0] == food_pos[0] and head_pos[1] == food_pos[1]:
         score += 1
         food_spawn = False
    else:
         snake_body.pop()

#spawing the food
if not food_spawn:
     food_pos = [random.randrange(1,(frame_size_x // square_size)) *  square_size,
                random.range(1,(frame_size_y // square_size)) * square_size]
     food_spawn = True
#GFX
game_window.fill(black)
for pos in snake_body:
     pygame.draw.rect(game_window, green, pygame.Rect(
          pos[0] + 2, pos[1] + 2, 
          square_size -2 ))

pygame.draw.rect(game_window,red, 
        pygame.Rect(food[0], 
            food_pos[1], 
                square_size, square_size))

#game over conditions

for block in snake_body[1:]:
     if head_pos[0] == block[0] and head_pos[1] == block[1]:
          init_vars()

show_score(1, yellow,'consolas', 20)
pygame.display.update()
fps_controller.tick(speed)
