import { Timestamp } from "rxjs";

export class Post {
  constructor(
  public topicID: string,
  public postID: string,
  public userName: string,
  public postTitle: string,
  public postText: string,
  public topicName: string,
  public isAnon: boolean,
  public postTime: Date,
  ) {}
}
