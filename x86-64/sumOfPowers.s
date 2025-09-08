.global sumOfPowers

sumOfPowers:
  mov $0, %rbx #x=0
  mov $1, %rcx #y=1
  mov $1, %rdx #i=1

Top:
  cmp %rdi, %rcx #while(y<=n)
  jg Done
  imul %rcx, %rdx #i*=y
  imul %rcx, %rdx #i*=y
  add %rdx, %rbx #x+=i
  add $1, %rcx #y+=1
  mov $1, %rdx #i=1 
  jmp Top

Done:
  mov %rbx, %rax #return x
  ret 
