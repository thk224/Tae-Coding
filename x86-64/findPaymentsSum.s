.global findPaymentsSum

findPaymentsSum:
  mov %rdi, %rbx #b=customer
  mov %rsi, %rcx #c=num_of_pamyments
  mov $0, %rdx #i=0
  mov $0, %r8 #sum
Top:
  cmp %rdx, %rcx #while(i<=c)
  jle Done #break
  add 8(%rbx,%rdx,4), %r8 #add payments[i]
  add $1, %rdx #i+=1
  jmp Top

Done:
  mov %r8, %rax #sum
  ret
