.global compareAges

compareAges:
  mov 4(%rdi), %rbx #a=a->age
  mov 4(%rsi), %rcx #b=b->age

Main:
  cmp %rbx, %rcx #if(a == b)
  je True #jump to True
  mov $0, %rax #False
  ret #return 0

True:
  mov $1, %rax #True
  ret #return 1
