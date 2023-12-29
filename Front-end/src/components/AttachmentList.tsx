import React from 'react';
import { List, ListItem, ListItemText, Link } from '@mui/material';
import { Attachment } from '../model/data';



interface AttachmentListProps {
  attachments: Attachment[];
}

const AttachmentList: React.FC<AttachmentListProps> = ({ attachments }) => {
  const handleAttachmentClick = (url: string) => {
    window.open(url, '_blank');
  };

  return (
    <List>
      {attachments.map((attachment, index) => (
        <ListItem key={index}>
          <ListItemText>
            <Link
              href="#"
              onClick={(event) => {
                event.preventDefault();
                handleAttachmentClick(attachment.attachment);
              }}
            >
              {attachment.attachment.length < 50 ?
                 attachment.attachment : attachment.attachment.slice(0, 50) + ' ....'}
            </Link>
          </ListItemText>
        </ListItem>
      ))}
    </List>
  );
};

export default AttachmentList;