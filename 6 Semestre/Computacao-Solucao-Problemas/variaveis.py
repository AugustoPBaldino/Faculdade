nome= 'Augusto'
numero = 1
n1=n2=n3=n4= 0.0
nome, idade = 'Augusto',47
estado = True

"""
Python não é tipado, que loucura

Sempre começar com letra
"""
# print(type(nome))
# print(type(numero))
# print(type(n2))
# print(type(estado))
# print(type(1+2j))

# Função isInstance()
a = 10
b = 'sol'
print(isinstance(a,int)) #True
print(isinstance(b,int)) #False

print(isinstance(n2,(int,float))) # True