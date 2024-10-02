import re

def solution(dartResult):
  tokens = re.findall('(\d+)(S|D|T)(\*|#|)', dartResult)
  scores = []

  for i in range(0,len(tokens)):
      score, bonus, option = tokens[i]
      scores.append(int(score))

      if bonus == 'S': scores[i] = scores[i] ** 1
      elif bonus == 'D': scores[i] = scores[i] ** 2
      elif bonus == 'T': scores[i] = scores[i] ** 3

      if option == '*': 
          scores[i] = scores[i] * 2
          if i > 0: scores[i - 1] = scores[i - 1] * 2
      elif option == '#': scores[i] = scores[i] * (-1)
  return sum(scores)