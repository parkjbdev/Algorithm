import math

def solution(progresses, speeds):
  answer = []
  maxDay = 0
  for (progress, speed) in zip(progresses, speeds):
    day = math.ceil((100 - progress) / speed)
    if maxDay < day:
      maxDay = day
      answer.append(0)
    answer[-1] += 1
  return answer
